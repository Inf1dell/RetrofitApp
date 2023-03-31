package apk.karmak.retrofitapp.main.Analyzes.modal;

public class CatalogModel {
    private Integer id;
    private String name;
    private String description;
    private String price;
    private String image;
    private String category;
    private String time_result;
    private String preparation;
    private String bio;

    public CatalogModel(
            Integer id,
            String name,
            String description,
            String price,
            String image,
            String category,
            String time_result,
            String preparation,
            String bio
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.category = category;
        this.time_result = time_result;
        this.preparation = preparation;
        this.bio = bio;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTime_result() {
        return time_result;
    }

    public void setTime_result(String time_result) {
        this.time_result = time_result;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
