package com.example.jpamapstruct.entity;

import com.example.jpamapstruct.enums.EventType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    String name;

//    @ManyToOne(cascade = CascadeType.ALL) //with this it "passes" but instead of use 'clubId' from request, it creates a new entry in CLUB table

    //org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : com.example.jpamapstruct.entity.Member.club -> com.example.jpamapstruct.entity.Club
    //	at org.hibernate.engine.spi.CascadingActions$8.noCascade(CascadingActions.java:383) ~[hibernate-core-6.1.6.Final.jar:6.1.6.Final]
    //	at org.hibernate.engine.internal.Cascade.cascade(Cascade.java:169) ~[hibernate-core-6.1.6.Final.jar:6.1.6.Final]
    //	at org.hibernate.event.internal.AbstractFlushingEventListener.cascadeOnFlush(AbstractFlushingEventListener.java:155) ~[hibernate-core-6.1.6.Final.jar:6.1.6.Final]
    //	at org.hibernate.event.internal.AbstractFlushingEventListener.prepareEntityFlushes(AbstractFlushingEventListener.java:145) ~[hibernate-core-6.1.6.Final.jar:6.1.6.Final]
    //	at org.hibernate.event.internal.AbstractFlushingEventListener.flushEverythingToExecutions(AbstractFlushingEventListener.java:79) ~[hibernate-core-6.1.6.Final.jar:6.1.6.Final]
    //	at org.hibernate.event.internal.DefaultFlushEventListener.onFlush(DefaultFlushEventListener.java:38) ~[hibernate-core-6.1.6.Final.jar:6.1.6.Final]
    //	at org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:107) ~[hibernate-core-6.1.6.Final.jar:6.1.6.Final]
    //	at org.hibernate.internal.SessionImpl.doFlush(SessionImpl.java:1425) ~[hibernate-core-6.1.6.Final.jar:6.1.6.Final]
    //	at org.hibernate.internal.SessionImpl.managedFlush(SessionImpl.java:477) ~[hibernate-core-6.1.6.Final.jar:6.1.6.Final]
    //	at org.hibernate.internal.SessionImpl.flushBeforeTransactionCompletion(SessionImpl.java:2234) ~[hibernate-core-6.1.6.Final.jar:6.1.6.Final]
    //	at org.hibernate.internal.SessionImpl.beforeTransactionCompletion(SessionImpl.java:1930) ~[hibernate-core-6.1.6.Final.jar:6.1.6.Final]
    //	at org.hibernate.engine.jdbc.internal.JdbcCoordinatorImpl.beforeTransactionCompletion(JdbcCoordinatorImpl.java:439) ~[hibernate-core-6.1.6.Final.jar:6.1.6.Final]
    //	at org.hibernate.resource.transaction.backend.jdbc.internal.JdbcResourceLocalTransactionCoordinatorImpl.beforeCompletionCallback(JdbcResourceLocalTransactionCoordinatorImpl.java:183) ~[hibernate-core-6.1.6.Final.jar:6.1.6.Final]
    //	at org.hibernate.resource.transaction.backend.jdbc.internal.JdbcResourceLocalTransactionCoordinatorImpl$TransactionDriverControlImpl.commit(JdbcResourceLocalTransactionCoordinatorImpl.java:281) ~[hibernate-core-6.1.6.Final.jar:6.1.6.Final]
    //	at org.hibernate.engine.transaction.internal.TransactionImpl.commit(TransactionImpl.java:101) ~[hibernate-core-6.1.6.Final.jar:6.1.6.Final]
    @ManyToOne(cascade = CascadeType.DETACH)
//    @ManyToOne(cascade = CascadeType.MERGE)
//    @ManyToOne(cascade = CascadeType.REFRESH)
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @ManyToOne(cascade = CascadeType.REMOVE)

    //org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : com.example.jpamapstruct.entity.Member.club -> com.example.jpamapstruct.entity.Club
    // at org.hibernate.engine.spi.CascadingActions$8.noCascade(CascadingActions.java:383) ~[hibernate-core-6.1.6.Final.jar:6.1.6.Final]
//    @ManyToOne //here is issue as above
    //    @JoinColumn(name = "club_id")
    Club club;
//    @Column(name = "club_id", nullable = true, insertable = false, updatable = false)
//    Long clubId;

//    @OneToMany //works as expected with table_column: MEMBER_EVENTS ? but not for second save :)
//    @JoinTable(name = "member_events",
//            joinColumns = @JoinColumn(name = "member_id"),
//            inverseJoinColumns = @JoinColumn(name = "event_id")
//    )
//    List<Event> events = new ArrayList<>();

    @ManyToMany //works as expected with table_column: MEMBER_EVENTS ? but not for second save :)
    @JoinTable(name = "member_events",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    List<Event> events = new ArrayList<>();

//    @OneToMany
//    @JoinColumn(name = "member_id") //it works but overrite member_id with new one
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//    @OneToMany(mappedBy = "member", orphanRemoval = true)
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    List<Event> events = new ArrayList<>();
}
