package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@GetMapping("/{itemId}")
	public String item(@PathVariable long itemId, Model model){
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/item";
	}

	@GetMapping("/add")
	public String addForm(){
		return "basic/addForm";
	}
//	@PostMapping("/add")
//	public String addItemV1(@RequestParam String itemName,
//	                   @RequestParam int price,
//	                   @RequestParam Integer quantity,
//	                   Model model){
//		Item item = new Item();
//		item.setItemName(itemName);
//		item.setPrice(price);
//		item.setQuantity(quantity);
//
//		itemRepository.save(item);
//		model.addAttribute("item", item);
//		return "basic/item";
//	}

//	@PostMapping("/add")
//	public String addItemV2(@ModelAttribute("item") Item item/*, Model model 모델 어트리뷰트가 넣어주니까*/){
//											//"item"은 Model에 들어가는 값 이름
//		itemRepository.save(item);
////		model.addAttribute("item", item);//@ModelAttribute에서 자동 추가해줌 생략가능
//		return "basic/item";
//	}

//	@PostMapping("/add")
//	public String addItemV3(@ModelAttribute Item item){
//		itemRepository.save(item);
////		@ModelAttribute에 ("값")이 없으면  자동으로 해당 인자값 클래스의 이름에 첫글자를 소문자로 바꿔 생성
////		if HelloModel -> helloModel -> model.addAttribute("helloModel", item);
//		return "basic/item";
//	}
//	@PostMapping("/add")
//	public String addItemV4(Item item){//@ModelAttribute 생략가능(기본타입이 아닌 개발자가 만든 클래스 )
//		itemRepository.save(item);
//		return "basic/item";
//	}
//	@PostMapping("/add")
//	public String addItemV5(Item item){//@ModelAttribute 생략가능(기본타입이 아닌 개발자가 만든 클래스 )
//		itemRepository.save(item);
//		return "redirect:/basic/items/"+item.getId();
//	}
	@PostMapping("/add")
	public String addItemV6(Item item, RedirectAttributes redirectAttributes){
		Item savedItem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", savedItem.getId());
		redirectAttributes.addAttribute("status", true);
		//redirectAttribute에서 추가해준 어트리뷰트 중 경로변수와 이름이 같은 건 치환해줌 {itemId}->해당하는 값
		//추가해준 어트리뷰트를 쿼리파라미터 형식으로 보냄 ?status=true
		return "redirect:/basic/items/{itemId}";
	}


	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable Long itemId, Model model){
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/editForm";
	}

	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
		//위의 form전송의 input태그의 name-value를 통해서 item값 자동 매핑
		itemRepository.update(itemId, item);
		return "redirect:/basic/items/{itemId}";
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
