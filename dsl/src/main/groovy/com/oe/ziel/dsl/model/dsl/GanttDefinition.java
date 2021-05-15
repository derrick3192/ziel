package com.oe.ziel.dsl.model.dsl;

import com.oe.ziel.domain.booking.Booking;
import com.oe.ziel.domain.booking.options.BookingOption;
import com.oe.ziel.domain.booking.options.BoolOption;
import com.oe.ziel.domain.booking.options.IntOption;
import com.oe.ziel.domain.booking.options.OptionList;
import com.oe.ziel.dsl.model.Note;
import com.oe.ziel.dsl.model.RelationshipType;
import com.oe.ziel.dsl.model.dsl.spec.WorkSpec;
import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import org.joda.time.Instant;
import space.jasan.support.groovy.closure.ConsumerWithDelegate;

import java.util.List;
import java.util.function.Consumer;

public interface GanttDefinition {

    /**
     * TODO implement this
     * Accept the booking before generating the work
     * @param booking
     */
//    void accept(Booking booking);


    List<BookingOption> getBookingOptions();

    default <B extends BookingOption> B buildOption(Closure cl, B bookingOption) {
        Consumer<Object> cl2 = ConsumerWithDelegate.create(cl);
        //cl.rehydrate(bookingOption, this, this);
        cl2.accept(bookingOption);
        getBookingOptions().add(bookingOption);
        return bookingOption;
    }

    default OptionList optionList(@DelegatesTo(value = OptionList.class, strategy = Closure.DELEGATE_FIRST) Closure cl) {
        return buildOption(cl, new OptionList());
    }

    default BoolOption boolOption(@DelegatesTo(value = BoolOption.class, strategy = Closure.DELEGATE_FIRST) Closure cl) {
        return buildOption(cl, new BoolOption());
    }

    default IntOption intOption(@DelegatesTo(value = IntOption.class, strategy = Closure.DELEGATE_FIRST) Closure cl) {
        return buildOption(cl, new IntOption());
    }

    default WorkSpec work(@DelegatesTo(value = WorkSpec.class, strategy = Closure.DELEGATE_FIRST) Closure cl) {
        Consumer<Object> cl2 = ConsumerWithDelegate.create(cl);
        WorkSpec workSpec = new WorkSpec();

        // TODO take this out of the booking
        Booking booking = new Booking();
        booking.setCreatedAt(Instant.now());
        workSpec.setBooking(booking);

        cl2.accept(workSpec);
        return workSpec;
    }


    //default WorkS work

//    ValidationResult validation(@DelegatesTo(value = Validation) Closure cl) {
//        Validation validation = new Validation()
//        cl.rehydrate(validation, this, this)
//        cl.call()
//        return validation
//    }







    default Note note(String text) {
        return note(text, null);
    }

    Note note(String text, String color);

    default TypeDefinition type(String name) {
        return type(name, (type) -> {});
    }

    TypeDefinition type(
        String name,
        Consumer<TypeDefinition> builder
    );

    default RelationshipDefinition aggregation(String source, String destination) {
        return aggregation(source, destination, (r) -> {});
    }

    default RelationshipDefinition aggregation(
        String source,
        String destination,
        Consumer<RelationshipDefinition> additionalProperties
    ) {
        return relationship(source, RelationshipType.AGGREGATION, destination, additionalProperties);
    }

    default RelationshipDefinition composition(String source, String destination) {
        return composition(source, destination,  (r) -> {});
    }

    default RelationshipDefinition composition(
        String source,
        String destination,
        Consumer<RelationshipDefinition> additionalProperties
    ) {
        return relationship(source, RelationshipType.COMPOSITION, destination, additionalProperties);
    }

    default RelationshipDefinition inheritance(
        String source,
        String destination
    ) {
        return inheritance(source, destination,  (r) -> {});
    }

    default RelationshipDefinition inheritance(
        String source,
        String destination,
        Consumer<RelationshipDefinition> additionalProperties
    ) {
        return relationship(source, RelationshipType.INHERITANCE, destination, additionalProperties);
    }

    default RelationshipDefinition association(String source, String destination) {
        return association(source, destination,  (r) -> {});
    }

    default RelationshipDefinition association(
        String source,
        String destination,
        Consumer<RelationshipDefinition> additionalProperties
    ) {
        return relationship(source, RelationshipType.ASSOCIATION, destination, additionalProperties);
    }

    default RelationshipDefinition relationship(String source, RelationshipType relationshipType, String destination) {
        return relationship(source, relationshipType, destination,  (r) -> {});
    }

    RelationshipDefinition relationship(
        String source,
        RelationshipType relationshipType,
        String destination,
        Consumer<RelationshipDefinition> additionalProperties
    );

    default <H extends GanttHelper> H add(Class<H> helper) {
        return configure(helper,  (h) -> {});
    }

    <H extends GanttHelper> H configure(Class<H> helper, Consumer<H> additionalProperties);

    void postprocess();

}
