/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package salaprove;

/**
 *
 * @author Ge
 */
 abstract class AbstractBuilder {

    public AbstractBuilder() {
        super();
    }
     protected Sala Product;

     /**
      * Get the value of Product
      *
      * @return the value of Product
      */
     public Sala getProduct() {
         return Product;
     }

     /**
      * Set the value of Product
      *
      * @param Product new value of Product
      */
     public void setProduct(Sala Product) {
         this.Product = Product;
     }


    public abstract Sala CreateSala();

    //public abstract Corso CreateCorsi();



 class ConcreteBuilder extends AbstractBuilder{

        public ConcreteBuilder() {
            super();
        }


  public Sala CreateSala(){
      //precondizioni
      Product=new Sala();
      try{
        // ConnessioneSala con= db.ConnessioneSale.getInstance();
         //con.LoadComponents(Product);
      }catch(Exception e){e.printStackTrace();}
   return Product;
  }

  }

 }


