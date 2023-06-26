package assignment.java5.phongnhph23459.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "Color")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

//    @NotEmpty(message = "Name is required")
    @Column(name = "Code")
    private String code;

    @Column(name = "Name")
    private String name;

    @Column(name = "Date_Create")
    private Date dateCreate;

    @Column(name = "Date_correct")
    private Date dateCorrect;

    @Column(name = "Status")
    private Integer status;
}
