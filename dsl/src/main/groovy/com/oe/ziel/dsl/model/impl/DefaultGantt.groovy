package com.oe.ziel.dsl.model.impl

import com.oe.ziel.domain.booking.Booking
import com.oe.ziel.domain.booking.options.BookingOption
import com.oe.ziel.domain.booking.options.validation.BookingOptionValidationResult
import com.oe.ziel.domain.booking.options.validation.ValidationResult
import com.oe.ziel.domain.work.Work
import com.oe.ziel.dsl.model.Gantt
import com.oe.ziel.dsl.model.Note
import com.oe.ziel.dsl.model.RelationshipType
import com.oe.ziel.dsl.model.dsl.GanttDefinition
import com.oe.ziel.dsl.model.dsl.GanttHelper
import com.oe.ziel.dsl.model.dsl.RelationshipDefinition
import com.oe.ziel.dsl.model.dsl.TypeDefinition
import com.oe.ziel.dsl.model.dsl.spec.WorkSpec
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.PackageScope
import groovy.transform.ToString
import space.jasan.support.groovy.closure.ConsumerWithDelegate

import java.util.function.Consumer

@ToString
@PackageScope
@CompileStatic
@EqualsAndHashCode
class DefaultGantt implements Gantt, GanttDefinition {


    private String name;

    private String description;

    private Booking booking

    private List<WorkSpec> workSpecs = new ArrayList<>();

    private List<ValidationResult<?>> validations = new ArrayList<>();



    final Collection<DefaultNote> notes = new LinkedHashSet<>()
    final Collection<DefaultRelationship> relationships = new LinkedHashSet<>()
    final Map<String, Object> metadata = new LinkedHashMap<>();
    final List<BookingOption> bookingOptions = new ArrayList<>()

    private final Map<String, DefaultType> typesMap = [:].withDefault { key -> new DefaultType(this, key.toString()) }
    private final Map<Class<? extends GanttHelper>, GanttHelper> helperMap = [:]


    @Override
    WorkSpec work(@DelegatesTo(value = WorkSpec.class, strategy = Closure.DELEGATE_FIRST) Closure cl) {

        Consumer<WorkSpec> cl2 = ConsumerWithDelegate.create(cl);
        WorkSpec workSpec = new WorkSpec();
        workSpec.addConsumer(cl2)

        workSpecs.add(workSpec)

        return workSpec
    }



    @Override
    Collection<? extends com.oe.ziel.dsl.model.Type> getTypes() {
        return typesMap.values()
    }

    protected validateAllRequiredParametersSpecified() {
        bookingOptions.forEach{BookingOption it ->
            if (it.isRequired() && !booking.getCustomerInput().keySet().contains(it.getId())) {
                validations.add(new BookingOptionValidationResult<>(it, false, "Booking option of " + it.getLabel() + " not found"))
            }
        }
    }

    protected validateAllParametersWithinBounds() {
        bookingOptions.forEach{BookingOption bo ->
            if (bo.isTooLarge()) validations.add(new BookingOptionValidationResult<>(bo, false, "Booking option of " + bo.getLabel() + " too large. Maximum value is " + bo.getMax()))
            if (bo.isTooSmall()) validations.add(new BookingOptionValidationResult<>(bo, false, "Booking option of " + bo.getLabel() + " too small. Minimum value is " + bo.getMin()))
        }
    }

    protected setCustomerInput() {
        for (Map.Entry<String, Comparable<?>> ci : booking.getCustomerInput().entrySet()) {
            BookingOption<?> bo = bookingOptions.find{ (it.id == ci.getKey()) }
            if (bo == null) {
                throw new RuntimeException("Could not find booking option for customer input of: " + ci.getKey() + " = " + ci.getValue())
            }
            bo.setSelected(ci.getValue())
        }
    }

    @CompileStatic
    @Override
    void accept(Booking booking) {
        this.booking = booking

        validateAllRequiredParametersSpecified()
        setCustomerInput()
        validateAllParametersWithinBounds()

        workSpecs.forEach{WorkSpec ws -> ws.setBooking(booking) ; ws.build()}
    }

    @Override
    Booking getBooking() {
        return booking
    }


    @Override
    List<BookingOption> getBookingOptions() {
        return bookingOptions
    }

    @Override
    DefaultNote note(String text, String color) {
        Note note = new DefaultNote(text, color)
        this.notes.add(note)
        return note
    }

    @Override
    DefaultType type(
        String name,
        Consumer<TypeDefinition> builder
    ) {
        DefaultType type = typesMap[name]
        builder.accept(type)
        return type
    }

    @Override
    DefaultRelationship relationship(
        String source,
        RelationshipType relationshipType,
        String destination,
        Consumer<RelationshipDefinition> additionalProperties
    ) {
        DefaultRelationship relationship = new DefaultRelationship(this, typesMap[source], relationshipType, typesMap[destination])
        additionalProperties.accept(relationship)
        this.relationships.add(relationship)
        return relationship
    }

    @Override
    public <H extends GanttHelper> H configure(Class<H> helper, Consumer<H> configuration) {
        H helperInstance = (H) helperMap.computeIfAbsent(helper, { helper.newInstance()})
        configuration.accept(helperInstance)
        return helperInstance
    }

    void postprocess() {
        for (GanttHelper helper : helperMap.values()) {
            metadata.putAll(helper.metadata)
        }
    }

    @Override
    public List<? extends Work> getWorks() {
        return workSpecs
    }

    @Override
    public List<ValidationResult<?>> validations() {
        return validations;
    }

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }
}
