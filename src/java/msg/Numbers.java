/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msg;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ENVY
 */
@Entity
@Table(name = "NUMBERS")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Numbers.findAll", query = "SELECT n FROM Numbers n")
    //, @NamedQuery(name = "Numbers.getTotal", query = "SELECT sum(Numbers n) FROM Numbers n")
    , @NamedQuery(name = "Numbers.cleanNumbers", query = "DELETE FROM Numbers")    
    , @NamedQuery(name = "Numbers.findByNumber", query = "SELECT n FROM Numbers n WHERE n.number = :number")})
public class Numbers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMBER")
    private Integer number;

    public Numbers() {
    }

    public Numbers(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (number != null ? number.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Numbers)) {
            return false;
        }
        Numbers other = (Numbers) object;
        if ((this.number == null && other.number != null) || (this.number != null && !this.number.equals(other.number))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "msg.Numbers[ number=" + number + " ]";
    }
    
}
