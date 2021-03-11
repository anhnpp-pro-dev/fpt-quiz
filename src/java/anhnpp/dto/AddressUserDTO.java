package anhnpp.dto;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class AddressUserDTO implements Serializable {

    private int addUsId;
    private String addUs;

    public AddressUserDTO(int addUsId, String addUs) {
        this.addUsId = addUsId;
        this.addUs = addUs;
    }

    public int getAddUsId() {
        return addUsId;
    }

    public void setAddUsId(int addUsId) {
        this.addUsId = addUsId;
    }

    public String getAddUs() {
        return addUs;
    }

    public void setAddUs(String addUs) {
        this.addUs = addUs;
    }

}
