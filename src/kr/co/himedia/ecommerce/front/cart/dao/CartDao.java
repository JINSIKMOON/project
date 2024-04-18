/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF HIMEDIA.CO.KR.
 * HIMEDIA.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2024 HIMEDIA.CO.KR ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 himedia.co.kr에 있으며,
 * himedia.co.kr이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * himedia.co.kr의 지적재산권 침해에 해당된다.
 * Copyright (C) 2024 himedia.co.kr All Rights Reserved.
 *
 *
 * Program		: kr.co.himedia.sn.ecommerce5th.mercury
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: CartDao.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240312114747][wlstlr@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.cart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.himedia.ecommerce.common.dto.PagingDto;
//import kr.co.himedia.ecommerce.front.buy.dto.BuyDtlDto;
//import kr.co.himedia.ecommerce.front.buy.dto.BuyMstDto;
import kr.co.himedia.ecommerce.front.cart.dto.CartDtlDto;
import kr.co.himedia.ecommerce.front.cart.dto.CartMstDto;
import kr.co.himedia.ecommerce.front.common.dao.BaseDao;

/**
 * @version 1.0.0
 * @author wlstlr@himedia.co.kr
 * 
 * @since 2024-03-12
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Repository("kr.co.himedia.ecommerce.front.cart.dao.CartDao")
public class CartDao extends BaseDao {

	public List<CartMstDto> list(PagingDto pagingDto) {
		return sqlSessionFront.selectList("kr.co.himedia.ecommerce.front.mybatis.cart.Cart.list", pagingDto);
	}
	
	public int count(PagingDto pagingDto) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.cart.Cart.count", pagingDto);
	}
	
	public int sequenceDetail() {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.cart.CartDetail.sequence");
	}
	
	public int sequenceMaster() {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.cart.CartMaster.sequence");
	}
	
	public List<CartDtlDto> select(CartMstDto cartMstDto) {
		return sqlSessionFront.selectList("kr.co.himedia.ecommerce.front.mybatis.cart.CartDetail.select", cartMstDto);
	}
	
	public int insertMaster(CartMstDto cartMstDto) {
		return sqlSessionFront.insert("kr.co.himedia.ecommerce.front.mybatis.cart.CartMaster.insert", cartMstDto);
	}
	
	public int insertDetail(CartDtlDto cartDtlDto) {
		return sqlSessionFront.insert("kr.co.himedia.ecommerce.front.mybatis.cart.CartDetail.insert", cartDtlDto);
	}
}
