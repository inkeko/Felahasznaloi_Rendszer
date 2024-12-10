package org.example.model;



import jakarta.persistence.*;
import javafx.scene.control.TableView;


@Entity
@Table(name = "users")  // Tábla neve "users"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    // Konstruktorok, getterek, setterek
    public User() {}

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public static TableView.ResizeFeatures<Object> resizeColumns(TableView.ResizeFeatures<Object> features) {
        return features;
    }
}
