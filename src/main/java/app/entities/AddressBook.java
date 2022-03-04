package app.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "book", cascade = CascadeType.PERSIST)
    // @JsonManagedReference
    private List<BuddyInfo> buddies;

    public AddressBook() {
        this.buddies = new ArrayList<>();
    }
    @Override
    public boolean equals(Object o) {
        AddressBook other = (AddressBook)o;
        for(int i = 0; i < this.buddies.size(); i++) {
            if(!other.buddies.get(i).equals(this.buddies.get(i)))
                return false;
        }
        return true;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public Collection<BuddyInfo> getBuddies() {
        return this.buddies;
    }
    public void setBuddies(List<BuddyInfo> buddies) {
        this.buddies = buddies;
    }
}