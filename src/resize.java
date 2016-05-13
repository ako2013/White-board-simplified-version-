public class resize
{
   int P1,P2,P3,P4;
   //int movedX,movedY;
   
   //still deciding the constructor going to be like
   //get int coordinates for 
   //need "wrappers" to wrap around the shape for resizing
   
    /* 
   
     p1          p3
      *----------*
      |          |
      |          |
      |          |
      |          |
      *----------*
      p2         p4
      
   */
   public resize(DShape shape)
   {
      this.P1 = shape.getX();
      this.P2 = shape.getY();
      this.P3 = shape.getWidth();
      this.P4 = shape.getHeight();
   } 
   
   public void createGrip()
   {
      
   }
   
   /*
     (new x,new y) 
       
         *     |                 
               | dY
               |
         ------*----------*
           dX  |          |
               |          |
               |          |
               |          |
               *----------*
        
        new width = new x coordinate + dX
        new height = new y coordinate + dY       
   */
   // x,y,width,height
   //pass in the amount moved in X/Y
   public void moveP1(int x, int y)
   {
      //return newShape(x,y,P3+x,P4+y);
   }
   public void moveP3(int x, int y)
   {
      //return newShape(P1,P2+y,P3+x,P4+y);
   }
   public void moveP2(int num)
   {
      //return newShape(P1+x,P2,P3+x,P4+y);
   }
   public void moveP4(int num)
   {
      //return newShape(P1,P2,P3+x,P4+y);
   }
}