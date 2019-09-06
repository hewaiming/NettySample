package objectWithSerializable;

import java.io.Serializable;

/**
 * User: hupeng Date: 14-6-3 Time: 上午1:31
 */
public class User implements Serializable {

    private int id;

    private String name;

    private String cardNo;

    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", cardNo='"
                + cardNo + '\'' + ", description='" + description + '\'' + '}';
    }
}