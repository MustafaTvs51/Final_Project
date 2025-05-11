package patika_14_final_project.model;

public class Category extends BaseModel {
    public Category() {
    }

    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, User createdUser, User updatedUser) {
        this.name = name;
        this.setCreatedUser(createdUser);
        this.setUpdatedUser(updatedUser);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
