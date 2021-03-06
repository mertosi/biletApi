package in.finartz.proje.biletapi.view;

import in.finartz.proje.biletapi.model.Bilet;
import in.finartz.proje.biletapi.service.BiletService;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class BiletView {

    @Autowired
    private BiletService biletService;

    private List<Bilet> biletler;
    private Bilet bilet = new Bilet();
    private Bilet newBilet = new Bilet();

    @PostConstruct
    public void init() {
        biletGetir();
    }

    public Bilet getBilet() {
        return bilet;
    }

    public void setBilet(Bilet bilet) {
        this.bilet = bilet;
    }

    public List<Bilet> getBiletler() {
        return biletler;
    }

    public void setBiletler(List<Bilet> biletler) {
        this.biletler = biletler;
    }
    public Bilet getNewBilet() {
        return newBilet;
    }

    public void setNewBilet(Bilet newBilet) {
        this.newBilet = newBilet;
    }

    public void biletGetir() {
        newBilet = new Bilet();
        biletler = biletService.get();
    }

    public void biletSil(int id) {
        biletService.delete(id);
        biletGetir();
        FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage("Silme işlemi başarılı!"));
    }

    public void biletEkle() {
        biletService.save(newBilet);
        biletGetir();
        FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage("Ekleme işlemi başarılı!"));
    }

    public void biletGetWithId(int id) {
        bilet = biletService.get(id);
    }

    public void biletDegistir() {
        biletService.update(bilet);
        PrimeFaces.current().executeScript("PF('biletUpdate').hide()");
        biletGetir();
        FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage("Güncelleme işlemi başarılı!"));
    }

    public String biletDetail(int id) {
        bilet = biletService.get(id);
        newBilet = new Bilet();
        return "biletDetail?faces-redirect=true&includeViewParams=true";
    }
}
