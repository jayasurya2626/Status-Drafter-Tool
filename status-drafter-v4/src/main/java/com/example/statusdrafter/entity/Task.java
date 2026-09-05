package com.example.statusdrafter.entity;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import java.time.*;
@Entity @Table(name="tasks") public class Task {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Size(max=200) private String taskName; @Size(max=2000) @Column(columnDefinition="TEXT") private String description;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="employee_id") private User employee;
 @NotBlank @Size(max=150) private String project;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private Priority priority=Priority.MEDIUM;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private Status status=Status.NOT_STARTED;
 @NotNull private LocalDate startDate; @NotNull private LocalDate deadline;
 @Column(nullable=false) private LocalDateTime createdAt; @Column(nullable=false) private LocalDateTime updatedAt;
 @PrePersist void pre(){createdAt=updatedAt=LocalDateTime.now();} @PreUpdate void upd(){updatedAt=LocalDateTime.now();}
 public Task(){} public Long getId(){return id;} public void setId(Long v){id=v;} public String getTaskName(){return taskName;} public void setTaskName(String v){taskName=v;} public String getDescription(){return description;} public void setDescription(String v){description=v;} public User getEmployee(){return employee;} public void setEmployee(User v){employee=v;} public String getProject(){return project;} public void setProject(String v){project=v;} public Priority getPriority(){return priority;} public void setPriority(Priority v){priority=v;} public Status getStatus(){return status;} public void setStatus(Status v){status=v;} public LocalDate getStartDate(){return startDate;} public void setStartDate(LocalDate v){startDate=v;} public LocalDate getDeadline(){return deadline;} public void setDeadline(LocalDate v){deadline=v;} public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
 public enum Status{NOT_STARTED,IN_PROGRESS,COMPLETED,ON_HOLD,BLOCKED} public enum Priority{LOW,MEDIUM,HIGH,CRITICAL}
}
