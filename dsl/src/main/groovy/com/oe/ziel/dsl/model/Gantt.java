package com.oe.ziel.dsl.model;

import com.oe.ziel.domain.booking.Booking;
import com.oe.ziel.domain.booking.options.BookingOption;
import com.oe.ziel.domain.booking.options.validation.BookingOptionValidationResult;
import com.oe.ziel.domain.booking.options.validation.ValidationResult;
import com.oe.ziel.domain.work.Work;
import com.oe.ziel.dsl.model.dsl.GanttDefinition;
import com.oe.ziel.dsl.model.impl.DefaultGanttFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface Gantt {


    static Gantt create(Consumer<GanttDefinition> definition) {
        return DefaultGanttFactory.build(definition);
    }

    @Deprecated
    Collection<? extends Note> getNotes();
    @Deprecated
    Collection<? extends Type> getTypes();
    @Deprecated
    Collection<? extends Relationship> getRelationships();

    @Deprecated
    Map<String, Object> getMetadata();


    /**
     * Populate the context of the booking
     * @param booking sets the booking context so we can construct the actual Gantt
     */
    void accept(Booking booking);


    public Booking getBooking();

    List<? extends Work> getWorks();

    List<ValidationResult<?>> validations();
}
