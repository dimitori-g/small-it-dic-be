package com.mitrei.dictionary.model;

// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dictionary")
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(name = "chinese", nullable = false)
    private String chinese;

    // @Column(name = "reading", nullable = false)
    private String reading;

    // @Column(name = "english", nullable = false)
    private String english;

    // Constructors
    // public Dictionary() {
    // }

    // public DictionaryEntry(String chinese, String reading, String english) {
    //     this.chinese = chinese;
    //     this.reading = reading;
    //     this.english = english;
    // }

}
