package core.basesyntax;

public class MyNode<E> {
    E item;
    MyNode<E> prev;
    MyNode<E> next;

    public MyNode(MyNode<E> prev, E item, MyNode<E> next) {
        this.prev = prev;
        this.item = item;
        this.next = next;
    }
}
