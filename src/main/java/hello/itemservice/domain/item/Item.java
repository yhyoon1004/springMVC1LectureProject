package hello.itemservice.domain.item;

import lombok.Data;

@Data//위험함
//@Getter@Setter//이렇게 쓰는 것 권장
public class Item {
	private Long id;
	private String itemName;
	private Integer price;
	private Integer quantity;

	public Item() {
	}

	public Item(String itemName, Integer price, Integer quantity) {
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
}
