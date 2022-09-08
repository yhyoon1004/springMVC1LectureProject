package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {
	ItemRepository itemRepository = new ItemRepository();
	
	@AfterEach
	void afterEach(){
		itemRepository.clearStore();
	}
	
	@Test
	void save(){
		//given
		Item item = new Item("ItemA", 10000, 10);
		
		//when
		Item savedItem = itemRepository.save(item);
		//then
		Item findItem = itemRepository.findById(item.getId());
		assertThat(savedItem).isEqualTo("findItem");
	}
	@Test
	void findAll(){
		//given
		Item item1 = new Item("item1", 10000, 10);
		Item item2 = new Item("item2", 20000, 20);
		//when

		
		//then


	}
	@Test
	void updateItem(){
		//given
		
		//when
		
		//then
	}
}
