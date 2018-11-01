package edu.uprm.cse.datastructures.cardealer.util.interfaces;

public interface PositionalList<E> extends Iterable<E> {
	/** Returns the number of elements in the list. **/
	int size();
	/** Tests whether the list is empty. **/
	boolean isEmpty(); 
	/** Returns the first position (or reference to it) in the list. Returns null if list is empty. **/
	Position<E> first();
	/** Returns the last position in the list. Returns null if list is empty. **/
	Position<E> last(); 
	/** Returns the position immediately before position p, or null if p is the first position in the list. **/
	Position<E> before(Position<E> p) throws IllegalArgumentException; 
	/** Returns the position immediately after position p, or null if p is the first position in the list. **/
	Position<E> after(Position<E> p) throws IllegalArgumentException; 
	/** Inserts element e at the front of the list and returns its new position. **/
	Position<E> addFirst(E e); 
	/** Inserts element e at the end (or back) of the list and returns its new position. **/
	Position<E> addLast(E e); 
	/** Inserts element e immediately before position p and returns its new position. **/
	Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;
	/** Inserts element e immediately after position p and returns its new position. **/ 
	Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;
	/** Replaces the element stored at position p and returns the replaced element. **/
	E set(Position<E> p, E e) throws IllegalArgumentException;	
	/** Removes the element stored at position p and returns it (invalidating p) **/ 		
	E remove(Position<E> p) throws IllegalArgumentException;
	/** Retuns an iterable collection of the positions in the list. */	
	Iterable<Position<E>> positions(); 	

}
