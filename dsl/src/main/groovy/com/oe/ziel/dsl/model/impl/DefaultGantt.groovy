package com.oe.ziel.dsl.model.impl

import com.oe.ziel.domain.booking.Booking
import com.oe.ziel.domain.booking.options.BookingOption
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

    private Booking booking

    private List<WorkSpec> workSpecs = new ArrayList<>();







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

        return workSpec;
    }



    @Override
    Collection<? extends com.oe.ziel.dsl.model.Type> getTypes() {
        return typesMap.values()
    }

    @Override
    void accept(Booking booking) {
        this.booking = booking

        for (WorkSpec workSpec in workSpecs) {
            workSpec.setBooking(booking)
            workSpec.build()
            println workSpec
            //workSpec.setBooking(getBooking());
            //cl2.accept(workSpec);
        }
    }

    public Booking getBooking() {
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
}
