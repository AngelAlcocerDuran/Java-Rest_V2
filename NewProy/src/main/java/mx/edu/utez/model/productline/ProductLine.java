package mx.edu.utez.model.productline;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ProductLine")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductLine {

    @XmlElement
    private String productLine, textDescription;

    public ProductLine() {
    }

    public ProductLine(String productLine, String textDescription) {
        this.productLine = productLine;
        this.textDescription = textDescription;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    @Override
    public String toString() {
        return "ProductLine{" +
                "productLine=" + productLine +
                ", textDescription='" + textDescription + '\'' +
                '}';
    }
}
