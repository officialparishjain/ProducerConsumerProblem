package producerConsumerProblem;

public class Company {

    /*
        If two threads call the incrementCounter() method at the same time, the counter may be incremented by only one thread.
        This is because the two threads are accessing the same shared resource (the counter variable) at the same time.
        We can prevent this race condition by synchronizing the incrementCounter() method:
     */

    int item;

    boolean flag = false;

    // If flag = false => producerConsumerProblem.Producer turn
    // If flag = true => producerConsumerProblem.Consumer turn
    synchronized public void produce_item(int item) throws InterruptedException {

        // Here if the flag is true means that it is turn of the consumer
        // so producer have to wait
        if(flag){
            wait();
        }
        this.item = item;
        System.out.println("Item produce :  " + this.item);

        // After the producer have done his work we will give turn to consumer
        flag = true;
        notify();
    }

    /*

        The notify() method in the consume_item() method wakes up one of the threads that are waiting on the object's monitor.
        This is done because the consume_item() method is synchronized, which means that only one thread can execute it at a time.

        In this case, the consume_item() method will only be executed by the thread that has produced the item.
        The other thread, which is waiting for the item to be produced, will be woken up by the notify() method.
        This allows the other thread to continue executing and consume the item.
     */
    synchronized public void consume_item() throws InterruptedException {

        if(!flag){
            wait();
        }
        System.out.println("Item Consumed : "+ this.item);
        flag = false;
        notify();
    }
}
