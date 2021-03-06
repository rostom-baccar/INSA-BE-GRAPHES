package org.insa.graphs.algorithm.utils;

import java.util.ArrayList;

/**
 * Implements a binary heap containing elements of type E.
 *
 * Note that all comparisons are based on the compareTo method, hence E must
 * implement Comparable
 * 
 * @author Mark Allen Weiss
 * @author DLB
 */
public class BinaryHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    // Number of elements in heap.
    private int currentSize;

    // The heap array.
    protected final ArrayList<E> array;

    /**
     * Construct a new empty binary heap.
     */
    public BinaryHeap() {
        this.currentSize = 0;
        this.array = new ArrayList<E>();
    }

    /**
     * Construct a copy of the given heap.
     * 
     * @param heap Binary heap to copy.
     */
    public BinaryHeap(BinaryHeap<E> heap) {
        this.currentSize = heap.currentSize;
        this.array = new ArrayList<E>(heap.array);
    }

    /**
     * Set an element at the given index.
     * 
     * @param index Index at which the element should be set.
     * @param value Element to set.
     */
    private void arraySet(int index, E value) {
        if (index == this.array.size()) {
            this.array.add(value);
        }
        else {
            this.array.set(index, value);
        }
    }

    /**
     * @return Index of the parent of the given index.
     */
    protected int indexParent(int index) {
        return (index - 1) / 2;
    }

    /**
     * @return Index of the left child of the given index.
     */
    protected int indexLeft(int index) {
        return index * 2 + 1;
    }

    /**
     * Internal method to percolate up in the heap.
     * 
     * @param index Index at which the percolate begins.
     */
    private void percolateUp(int index) {
        E x = this.array.get(index);

        for (; index > 0
                && x.compareTo(this.array.get(indexParent(index))) < 0; index = indexParent(
                        index)) {
            E moving_val = this.array.get(indexParent(index));
            this.arraySet(index, moving_val);
        }

        this.arraySet(index, x);
    }

    /**
     * Internal method to percolate down in the heap.
     * 
     * @param index Index at which the percolate begins.
     */
    private void percolateDown(int index) {
        int ileft = indexLeft(index);
        int iright = ileft + 1;

        if (ileft < this.currentSize) {
            E current = this.array.get(index);
            E left = this.array.get(ileft);
            boolean hasRight = iright < this.currentSize;
            E right = (hasRight) ? this.array.get(iright) : null;

            if (!hasRight || left.compareTo(right) < 0) {
                // Left is smaller
                if (left.compareTo(current) < 0) {
                    this.arraySet(index, left);
                    this.arraySet(ileft, current);
                    this.percolateDown(ileft);
                }
            }
            else {
                // Right is smaller
                if (right.compareTo(current) < 0) {
                    this.arraySet(index, right);
                    this.arraySet(iright, current);
                    this.percolateDown(iright);
                }
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return this.currentSize == 0;
    }

    @Override
    public int size() {
        return this.currentSize;
    }

    @Override
    public void insert(E x) {
        int index = this.currentSize++;
        this.arraySet(index, x);
        this.percolateUp(index);
    }

    @Override
    public void remove(E x) throws ElementNotFoundException {
    	int index=-1; //init de la variable qui contiendra l'index de l'??l??ment ?? supprimer
        //cette m??thode permet de supprimer n'importe quel ??l??ment de l'arbre
    	//avant d'attaquer l'algo on effectue quelques tests n??cessaires
    	//1er cas: l'arbre est vide, on renvoie une erreur 
    	if (this.isEmpty()) {
    		throw new ElementNotFoundException(x);
    	}
    	//sinon on identifie l'index de l'??l??ment ?? supprimer
    	else { 
	    	try {
	    		for(int i=0; i<this.size();i++) {
	    			if(this.array.get(i).equals(x))
	    				index = i;
	    		}
	    	}catch(EmptyPriorityQueueException e) {
	    		index = -1;
	    	}
    	}
    	//si l'??l??ment n'existe pas on renvoie ??galement une erreur
    	if(index == -1) {
	    	throw new ElementNotFoundException(x);
    	}
    	//une fois l'index de l'??l??ment identifi??, on associe le dernier ??l??ment de l'arbre ?? l'??l??ment d'index
    	//la taille de l'arbre -1 (l'??l??ment juste avant lui). L'??l??ment en question n'existe donc plus dans l'arbre
    	E dernier_element= this.array.get(--this.currentSize);
    	this.arraySet(index, dernier_element);
    	//enfin, une fois la structure de l'arbre respect??e, on applique la r??gle du p??re et des fils:
    	//un p??re doit avoir une priorit?? sup??rieure ou ??gale ?? ses fils
    	//pour cela, et puisqu'on ne connait pas exactement la valeur de l'??l??ment supprim??, on s'assure 
    	//de bien le placer dans l'arbre en faisant percolateDown et percolateUp
    	//ainsi il monte dans l'arbre s'il doit monter et il descend s'il doit descendre
    	this.percolateUp(index);
		this.percolateDown(index);
    	
    }
    
    //fonction renvoyant true si le tas est valide et false sinon
    //un tas valide est un tas qui respecte la r??gle du p??re et du fils cit??e pr??cedemment:
    //un p??re doit avoir une priorit?? sup??rieure ou ??gale ?? ses fils (dans ce cas, le p??re a un co??t plus petit ou
    //??gal que ses fils)
    public boolean isValid() {
    	//variable bool??enne: true si le tas est valide et false sinon
    	boolean tas_valide = false ; 
    	
    	//pour ne pas faire planter le programme, on consid??re qu'un tas vide est valdie
    	if (this.isEmpty()) {
    		tas_valide = true ; 
    	} 
    	else {
    		//on remet la variable ?? true pour tester la validit?? du tas
    		//on s'arr??te quand la variable a chang?? en false ou bien quand on a 
    		//parcouru tout le tas
    		tas_valide = true ; 
    		int i=0;
    		while (tas_valide == true && i<=this.currentSize) {
    			//on analyse l'index des fils de chaque noeud
    			//on voit s'ils suivent la r??gle
    			
    			//fils gauche:
    			if (indexLeft(i)<this.currentSize && indexLeft(i)<i) {
    				tas_valide=false;
    			}
    			
    			//fils droit:
    			if ((indexLeft(i)+1)<this.currentSize && (indexLeft(i)+1)<i) {
    				tas_valide=false;
    			}
    			i++;
    		}
    	}
    	return tas_valide ; 
    }

    @Override
    public E findMin() throws EmptyPriorityQueueException {
        if (isEmpty())
            throw new EmptyPriorityQueueException();
        return this.array.get(0);
    }

    @Override
    public E deleteMin() throws EmptyPriorityQueueException {
        E minItem = findMin();
        E lastItem = this.array.get(--this.currentSize);
        this.arraySet(0, lastItem);
        this.percolateDown(0);
        return minItem;
    }

    /**
     * Creates a multi-lines string representing a sorted view of this binary heap.
     * 
     * @return a string containing a sorted view this binary heap.
     */
    public String toStringSorted() {
        return BinaryHeapFormatter.toStringSorted(this, -1);
    }

    /**
     * Creates a multi-lines string representing a sorted view of this binary heap.
     * 
     * @param maxElement Maximum number of elements to display. or {@code -1} to
     *                   display all the elements.
     * 
     * @return a string containing a sorted view this binary heap.
     */
    public String toStringSorted(int maxElement) {
        return BinaryHeapFormatter.toStringSorted(this, maxElement);
    }

    /**
     * Creates a multi-lines string representing a tree view of this binary heap.
     * 
     * @return a string containing a tree view of this binary heap.
     */
    public String toStringTree() {
        return BinaryHeapFormatter.toStringTree(this, Integer.MAX_VALUE);
    }

    /**
     * Creates a multi-lines string representing a tree view of this binary heap.
     * 
     * @param maxDepth Maximum depth of the tree to display.
     * 
     * @return a string containing a tree view of this binary heap.
     */
    public String toStringTree(int maxDepth) {
        return BinaryHeapFormatter.toStringTree(this, maxDepth);
    }

    @Override
    public String toString() {
        return BinaryHeapFormatter.toStringTree(this, 8);
    }

}
