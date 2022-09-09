package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor//lombok의 애너테이션으로 해당 클래스의 생성자를 자동으로 만들어줌
public class BasicItemController {
	private final ItemRepository itemRepository;

//	@RequiredArgsConstructor애너테이션으로 아래 생성자 생략가능
////	@Autowired//생성자 메서드에 자동주입을 설정하여  매개변수를 springBean에 등록된 객체를 자동 주입
////	스프링에서 해당 클래스의 생성자가 하나 밖에 없으면 @AutoWired 생략가능(자동으로 DI 해줌)
//	public BasicItemController(ItemRepository itemRepository){//반환타입이 없으므로 생성자임을 알 수 있음
//		this.itemRepository = itemRepository;
//	}

	@GetMapping
	public String items(Model model){
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "basic/items";
	}

	/**
	 * 테스트용 데이터를 추가
	 */
	@PostConstruct
	public void init(){
		System.out.println("BasicItemController.init");
		itemRepository.save(new Item("itemA",10000,10));
		itemRepository.save(new Item("itemB",20000,20));
	}

}
