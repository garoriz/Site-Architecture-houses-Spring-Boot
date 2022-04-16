package ru.kpfu.stud.rizrgaripov.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String surname;

    private String status;

    private String urlPhoto;

    @Column(unique = true)
    private String login;

    @Size(min = 8, max = 64, message = "Пароль должен содержать от 8 до 64 символов")
    @Column(nullable = false, length = 64)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
    private List<Article> articles;

    @OneToMany(mappedBy = "message", fetch = FetchType.EAGER)
    private List<Message> messages;

    @OneToMany(mappedBy = "photo", fetch = FetchType.EAGER)
    private List<Photo> photos;

    @OneToMany(mappedBy = "sale_ad", fetch = FetchType.EAGER)
    private List<SaleAd> saleAds;

    public User() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<SaleAd> getSaleAds() {
        return saleAds;
    }

    public void setSaleAds(List<SaleAd> saleAds) {
        this.saleAds = saleAds;
    }

    public User(String name, String surname, String status, String urlPhoto, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.urlPhoto = urlPhoto;
        this.login = login;
        this.password = password;
    }
}
