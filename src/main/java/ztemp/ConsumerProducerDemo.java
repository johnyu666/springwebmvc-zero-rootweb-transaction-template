package ztemp;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

public class ConsumerProducerDemo {
	public static void main(String[] args) {
		List<Integer> base = new ArrayList<Integer>();
		new Thread(new Producer(base)).start();
		new Thread(new Consumer(base)).start();

	}
}

class Producer implements Runnable {
	private List<Integer> base;

	public Producer(List<Integer> base) {
		this.base = base;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			synchronized (base) {
				int p = (int) (Math.random() * 90) + 10;
				if (base.size() < 10) {
					base.add(p);
					System.out.println("生产者正在生产：" + p);
					base.notify();
				} else {
					try {
						System.out.println("产品库已满。。。。");
						base.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
	}
}

class Consumer implements Runnable {
	private List<Integer> base;

	public Consumer(List<Integer> base) {
		super();
		this.base = base;
	}

	@Override
	public void run() {
		while (true) {			
			synchronized (base) {
				if (!base.isEmpty()) {
					System.out.println("消费者正在消费：" + base.get(0));
					base.remove(0);
					base.notify();
				} else {
					try {
						System.out.println("产品库已空。。。。");
						base.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
