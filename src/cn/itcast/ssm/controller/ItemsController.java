package cn.itcast.ssm.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.ssm.controller.validation.ValidGroup1;
import cn.itcast.ssm.po.ItemsCustom;
import cn.itcast.ssm.po.ItemsQueryVo;
import cn.itcast.ssm.service.ItemsService;



@Controller

@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemsService itemsService;

	
	@ModelAttribute("itemtypes")
	public Map<String, String> getItemTypes() {

		Map<String, String> itemTypes = new HashMap<String, String>();
		itemTypes.put("101", "鏁扮爜");
		itemTypes.put("102", "姣嶅┐");

		return itemTypes;
	}


	@RequestMapping("/queryItems")
	public ModelAndView queryItems(HttpServletRequest request,
			ItemsQueryVo itemsQueryVo) throws Exception {
		
		
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("itemsList", itemsList);

		
		modelAndView.setViewName("items/itemsList");

		return modelAndView;

	}

	

	@RequestMapping(value = "/editItems", method = { RequestMethod.POST,
			RequestMethod.GET })
	
	public String editItems(Model model,
			@RequestParam(value = "id", required = true) Integer items_id)
			throws Exception {

		
		ItemsCustom itemsCustom = itemsService.findItemsById(items_id);


		
		model.addAttribute("items", itemsCustom);

		return "items/editItems";
	}
	
	
	@RequestMapping("/itemsView/{id}")
	public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id)throws Exception{
		
		
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		
		return itemsCustom;
		
	}
	

	
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(
			Model model,
			HttpServletRequest request,
			Integer id,
			@ModelAttribute("items") @Validated(value = { ValidGroup1.class }) ItemsCustom itemsCustom,
			BindingResult bindingResult,
			MultipartFile items_pic
			) throws Exception {

		
		if (bindingResult.hasErrors()) {
		
			List<ObjectError> allErrors = bindingResult.getAllErrors();

			for (ObjectError objectError : allErrors) {
		
				System.out.println(objectError.getDefaultMessage());

			}
		
			model.addAttribute("allErrors", allErrors);
			
			
		
			model.addAttribute("items", itemsCustom);
			
		
			return "items/editItems";
		}
	
		String originalFilename = items_pic.getOriginalFilename();
	
		if(items_pic!=null && originalFilename!=null && originalFilename.length()>0){
			
		
			String pic_path = "F:\\develop\\upload\\temp\\";
			
			
		
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
		
			File newFile = new File(pic_path+newFileName);
			
			
			items_pic.transferTo(newFile);
			
		
			itemsCustom.setPic(newFileName);
			
		}
		

		
		itemsService.updateItems(id, itemsCustom);

		
		return "success";
	}


	@RequestMapping("/deleteItems")
	public String deleteItems(Integer[] items_id) throws Exception {

		

		return "success";

	}

	
	@RequestMapping("/editItemsQuery")
	public ModelAndView editItemsQuery(HttpServletRequest request,
			ItemsQueryVo itemsQueryVo) throws Exception {

		
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

		
		ModelAndView modelAndView = new ModelAndView();
	
		modelAndView.addObject("itemsList", itemsList);

		modelAndView.setViewName("items/editItemsQuery");

		return modelAndView;

	}

	
	@RequestMapping("/editItemsAllSubmit")
	public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo)
			throws Exception {

		return "success";
	}

}
