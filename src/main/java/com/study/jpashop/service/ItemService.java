package com.study.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.jpashop.domain.item.Book;
import com.study.jpashop.domain.item.Item;
import com.study.jpashop.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;

	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}

	@Transactional
	public void updateItem(Long itemId, String name, int price, int stockQuantity) {
		Item item = itemRepository.findOne(itemId);
		item.setName(name);
		item.setPrice(price);
		item.setStockQuantity(stockQuantity);
	}

	public Item findItem(Long itemId) {
		return itemRepository.findOne(itemId);
	}

	public List<Item> findItems() {
		return itemRepository.findAll();
	}

}