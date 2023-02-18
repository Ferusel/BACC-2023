class QueueTest {

  public static void main(String[] args) {
    { 
      Integer i;
      Queue<Integer> q = new Queue<Integer>(2);
      expect("enq 4", q.enq(4), true);
      expect("enq 8", q.enq(8), true);
      expect("enq 0", q.enq(0), false);
      i = q.deq();
      expect("deq", i, 4);
      i = q.deq();
      expect("deq", i, 8);
      i = q.deq();
      expect("deq", i, null);
    }
  }

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";

  public static void expect(String test, Object output, Object expect) {
    System.out.print(test);
    if ((output == null && expect == null) || output.equals(expect)) {
      System.out.println(".. " + ANSI_GREEN + "ok" + ANSI_RESET);
    } else {
      System.out.println(".. " + ANSI_RED + "failed" + ANSI_RESET);
      System.out.println("  expected: " + expect);
      System.out.println("  got this: " + output);
    }
  }
}
