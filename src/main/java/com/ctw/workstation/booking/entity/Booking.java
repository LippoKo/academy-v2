package com.ctw.workstation.booking.entity;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.shared.EntityBase;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "T_BOOKING")
public class Booking extends EntityBase {

    @Column(name = "RACK_ID", nullable = false)
    private UUID rack_id;

    @Column(name = "REQUESTER_ID", nullable = false)
    private UUID requester_id;

    @Column(name = "BOOK_FROM", nullable = false)
    private LocalDateTime book_from;

    @Column(name = "BOOK_TO", nullable = false)
    private LocalDateTime book_to;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RACK_ID", insertable=false, updatable = false)
    private Rack rack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUESTER_ID", insertable=false, updatable = false)
    private TeamMember teamMember;

    public UUID getRack_id() {
        return rack_id;
    }

    public void setRack_id(UUID rack_id) {
        this.rack_id = rack_id;
    }

    public UUID getRequester_id() {
        return requester_id;
    }

    public void setRequester_id(UUID requester_id) {
        this.requester_id = requester_id;
    }

    public LocalDateTime getBook_from() {
        return book_from;
    }

    public void setBook_from(LocalDateTime book_from) {
        this.book_from = book_from;
    }

    public LocalDateTime getBook_to() {
        return book_to;
    }

    public void setBook_to(LocalDateTime book_to) {
        this.book_to = book_to;
    }

}
