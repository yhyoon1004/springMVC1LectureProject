package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

	private static final Map<Long, Item> store = new HashMap<>();//static을 사용했다는 것 유의
												//위의 hashMap은 동시접근 불가 (멀티 쓰레드) ->
												//동시 접속 하려면 ConcurrentHashMap<>() 사용해야됨
	private static long sequence = 0l;//static 유의
					//long도 마찬가지로 atomicLong같은 것을 사용해서 동시 접근하게 해야됨

	public Item save(Item item){
		item.setId(++sequence);
		store.put(item.getId(), item);
		return item;
	}

	public Item findById(Long id){
		return store.get(id);
	}

	public List<Item> findAll(){
		return new ArrayList<>(store.values());
	}

	public void  update(Long itemId, Item updateParam){
		Item findItem = findById(itemId);
		findItem.setItemName(updateParam.getItemName());
		findItem.setPrice(updateParam.getPrice());
		findItem.setQuantity(updateParam.getQuantity());
	}

	public void clearStore(){
		store.clear();
	}
}
