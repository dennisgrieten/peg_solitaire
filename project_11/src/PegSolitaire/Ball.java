package PegSolitaire;

import java.util.Stack;

/**
 * Created by dennis on 28/01/15.
 */
public class Ball {
    // Stack voor de geschiedenis van 'x' + 'y' coördinaten
    // Van het type 'Byte' om ruimte te besparen(range -128 tot 127, signed two's complement)
    // Meer hebben we niet nodig want er zijn maximum 31 zetten mogelijk.
    // ('x' altijd eerst pushen, 'y' altijd eerst poppen)
    Stack<Byte> history = new Stack<Byte>();
}
