class Queue<T> {
  private T[] items;
  private int first;
  private int last;
  private int maxSize;
  private int len;

  public Queue(int size) {
    this.maxSize = size;
    @SuppressWarnings("unchecked")
    T[] a = (T[])new Object[size];
    this.items = a;
    this.first = -1;
    this.last = -1;
    this.len = 0;
  }

  public boolean enq(T q) {
    if (this.isFull()) {
      return false;
    }
    if (this.isEmpty()) {
      this.first = 0;
      this.last = 0;
    } else {
      this.last = (this.last + 1) % this.maxSize;
    }
    this.items[last] = q;
    this.len += 1;
    return true;
  }

  public T deq() {
    if (this.isEmpty()) {
      return null;
    }
    T item = this.items[this.first];
    this.first = (this.first + 1) % this.maxSize;
    this.len -= 1;
    return item;
  }

  boolean isFull() {
    return (this.len == this.maxSize);
  }

  boolean isEmpty() {
    return (this.len == 0);
  }

  @Override
  public String toString() {
    String str = "[ ";
    int i = this.first;
    int count = 0;
    while (count < this.len) {
      str += this.items[i] + " ";
      i = (i + 1) % this.maxSize;
      count++;
    } 
    return str + "]";
  }

  public int length() {
    return this.len;
  }
}
