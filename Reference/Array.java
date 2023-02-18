class Array<T extends Comparable<T>> {
  private T[] array;

  Array(int size) {
    @SuppressWarnings({"unchecked", "rawtypes"})
    T[] a = (T[]) new Comparable[size];
    this.array = a;
  }

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return this.array[index];
  }

  public T[] getArray() {
    return this.array;
  }

  public T min() {
    T min = null;
    for (T t : array) {
       if (min == null || t.compareTo(min) < 0) {
         min = t;
       }
    }
    return min;
  }

  int getLength() {
    return this.array.length;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
