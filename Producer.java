package producerConsumerProblem;


public class Producer extends Thread{

    Company company;

    public Producer(Company company){
        this.company = company;
    }

    @Override
    public void run() {
        int i=1;
        while(true){
            try {
                this.company.produce_item(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
    }
}
