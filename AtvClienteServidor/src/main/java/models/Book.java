package models;

public class Book {
	 private String title, author, category;
	    private int id;
	    private float value;

	    public String getTitle() {
	        return title;
	    }
	    public void setTitle(String title) {
	        this.title = title;
	    }
	    public String getAuthor() {
	        return author;
	    }
	    public void setAuthor(String author) {
	        this.author = author;
	    }
	    public String getCategory() {
	        return category;
	    }
	    public void setCategory(String category) {
	        this.category = category;
	    }
	    public int getId() {
	        return id;
	    }
	    public void setId(int id) {
	        this.id = id;
	    }
	    public float getValue() {
	        return value;
	    }
	    public void setValue(float value) {
	        this.value = value;
	    }
}
