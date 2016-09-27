/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.text.html.parser.Entity;
import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import streaming.entity.Film;

/**
 *
 * @author admin
 */
public class RequetesTest {

    @Test
    public void test39() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        List l = (List) em.createQuery( "SELECT f FROM Film f JOIN f.genre r  WHERE r.nom = 'Horreur'  UNION SELECT f FROM Film f JOIN f.acteurs a  WHERE   a.nom = 'Polanski' ")
                .getResultList();
        
        long k = (long) l.size();

        Assert.assertEquals(1L, k);
    }

    @Test
    public void test37() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        
        List l = (List) em.createQuery( "SELECT f FROM Film f   INTERSECT SELECT f FROM Film f JOIN f.acteurs a  WHERE   a.nom = 'Polanski' ")
                .getResultList();
        
        long k = (long) l.size();

        Assert.assertEquals(1L, k);
    }

    @Test
    public void test35() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        List l = (List) em.createQuery( "SELECT f FROM Film f JOIN f.genre r  WHERE r.nom = 'Horreur'  EXCEPT SELECT f FROM Film f JOIN f.acteurs a  WHERE   a.nom = 'Polanski' ")
                .getResultList();
        
        long k = (long) l.size();

        Assert.assertEquals(0L, k);
    }

    @Test
    public void test33() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long k = (long) em.createQuery( "SELECT COUNT(l) FROM Lien l JOIN l.film f JOIN f.genre s JOIN f.acteurs t WHERE s.nom = 'Horreur'  AND t.nom = 'Polanski' ")
                .getSingleResult();

        Assert.assertEquals(1L, k);
    }

    @Test
    public void test31() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long k = (long) em.createQuery( "SELECT COUNT(l) FROM Lien l JOIN l.film f JOIN f.genre s JOIN f.pays t WHERE s.nom = 'Policier'  AND t.nom = 'USA' ")
                .getSingleResult();

        Assert.assertEquals(3L, k);
    }

    @Test
    public void test29() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
         long l = (long) em.createQuery( "SELECT COUNT(e) FROM Episode e JOIN e.saison sa JOIN sa.serie se     WHERE se.titre ='Dexter' ")
                .getSingleResult();

        Assert.assertEquals(96L, l);
    }

    @Test
    public void test27() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        
         long l = (long) em.createQuery( "SELECT COUNT(e) FROM Episode e JOIN e.saison sa JOIN sa.serie se     WHERE se.titre ='Dexter' AND sa.numSaison = 8 ")
                .getSingleResult();

        Assert.assertEquals(12L, l);
    }

    @Test
    public void test25() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
         long l = (long) em.createQuery( "SELECT COUNT(s) FROM Saison s JOIN s.serie se     WHERE se.titre ='Dexter'  ")
                .getSingleResult();

        Assert.assertEquals(8L, l);
    }

    @Test
    public void test23() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
         long l = (long) em.createQuery( "SELECT COUNT(f) FROM Film f JOIN f.genre r JOIN f.acteurs s JOIN f.realisateurs t WHERE r.nom = 'Policier'  AND s.nom ='Buscemi' AND t.nom= 'Coen' ")
                .getSingleResult();

        Assert.assertEquals(2, l);
    }

    @Test
    public void test21() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long l = (long) em.createQuery( "SELECT COUNT(f) FROM Film f JOIN f.realisateurs r JOIN f.acteurs s WHERE r.nom = 'Coen'  AND s.nom='Buscemi' ")
                .getSingleResult();

        Assert.assertEquals(4, l);
        
        
        
        
    }

    @Test
    public void test19() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long l = (long) em.createQuery("SELECT COUNT(f) FROM Film f JOIN f.realisateurs r WHERE r.nom='Coen' ")
                .getSingleResult();

        Assert.assertEquals(4L, l);
        
        
        
    }

    @Test
    public void test17() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long l = (long) em.createQuery("SELECT COUNT(f) FROM Film f JOIN f.realisateurs r WHERE r.nom  = 'Coen' AND r.prenom = 'Joel' ")
                .getSingleResult();

        Assert.assertEquals(2L, l);
    }

    @Test
    public void test15() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();

        String q =(String) em.createQuery("SELECT f.titre FROM Film f JOIN f.genre s JOIN f.pays r  WHERE s.nom = 'Horreur' AND   r.nom = 'UK'").getSingleResult();
                
                

        Assert.assertEquals("Le bal des vampires",q);
    }

    @Test
    public void test13() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();

        long k = (long) em.createQuery(" SELECT COUNT (f) FROM Film f JOIN f.realisateurs r JOIN f.acteurs a WHERE r.nom= 'Polanski' AND a.nom= 'Polanski'")
                .getSingleResult();

        Assert.assertEquals(1, k);
    }

    @Test
    public void test11() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();

        long k = (long) em.createQuery("SELECT COUNT (f) FROM Film f JOIN f.acteurs r WHERE r.nom = 'Polanski'")
                .getSingleResult();

        Assert.assertEquals(1L, k);

    }

   @Test
    public void test9() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
       
      long l = (long) em.createQuery(" SELECT COUNT (f) FROM Film f JOIN f.realisateurs r WHERE r.nom = 'Polanski'")
               .getSingleResult();

        Assert.assertEquals(2L, l);
    }

    @Test
    public void test7() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long l = (long) em.createQuery( "SELECT COUNT(l)FROM Film f JOIN f.liens l WHERE f.titre LIKE '%Big Lebowski %'").getSingleResult();

        Assert.assertEquals(1L, l);
    }

    @Test
    public void test5() {

        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        int k = (int) em.createQuery("SELECT MIN(f.annee)  FROM Film f ")
                .getSingleResult();

        Assert.assertEquals(1968, k);
    }

    @Test
    public void test3() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        //long l;
       long l = (long) em.createQuery("SELECT COUNT(f) FROM Film f").getSingleResult();

        Assert.assertEquals(4L, l);
    }

    @Test
    public void test1() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();

        //cree un objet Query contenant la requet JPQL ci-dessous
        Query q = em.createQuery("SELECT f FROM Film f WHERE f.id=4");

        //Execute la requete en BD et renvoie un seul film
        Film f = (Film) q.getSingleResult();

        //Assertion sur le titre
        Assert.assertEquals("Fargo", f.getTitre());

    }

}
