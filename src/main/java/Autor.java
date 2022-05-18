import javax.xml.bind.annotation.*;

@XmlRootElement(name="autor")
@XmlAccessorType(XmlAccessType.FIELD)
public class Autor {

    @XmlAttribute
    private Long id;
    @XmlElement
    private String nome;

    public Autor(){}

    public Autor(String nome) {
        this.id = Math.round(Math.random()*10);
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
