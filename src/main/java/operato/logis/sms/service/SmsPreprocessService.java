package operato.logis.sms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import operato.logis.sms.query.SmsQueryStore;
import xyz.anythings.base.entity.JobBatch;
import xyz.anythings.base.entity.OrderPreprocess;
import xyz.anythings.base.service.api.IPreprocessService;
import xyz.anythings.sys.service.AbstractExecutionService;
import xyz.anythings.sys.util.AnyOrmUtil;
import xyz.elidom.dbist.dml.Query;
import xyz.elidom.exception.server.ElidomRuntimeException;
import xyz.elidom.util.ValueUtil;

/**
 * SMS 주문 가공 서비스
 * 
 * @author jyp
 *
 */

@Component("smsPreprocessService")
public class SmsPreprocessService extends AbstractExecutionService implements IPreprocessService {

	/**
	 * Sms 쿼리 스토어
	 */
	@Autowired
	private SmsQueryStore queryStore;
	
	@Override
	public List<OrderPreprocess> searchPreprocessList(JobBatch batch) {
		Query condition = AnyOrmUtil.newConditionForExecution(batch.getDomainId());
		
		condition.addSelect("id", "batchId", "jobType", "comCd", "cellAssgnCd", "cellAssgnNm", "equipCd", "equipNm", "subEquipCd", "skuQty", "totalPcs");
		condition.addFilter("batchId", batch.getId());
		condition.addOrder("totalPcs", false);
		return this.queryManager.selectList(OrderPreprocess.class, condition);
	}

	@Override
	public Map<String, ?> buildPreprocessSet(JobBatch batch, Query query) {
		// 1. 가공할 주문 정보 조회
		List<OrderPreprocess> preprocesses = this.queryManager.selectList(OrderPreprocess.class, query);
		
		// 2. 주문 가공 정보가 존재하지 않는다면 주문 정보로 생성
		if(ValueUtil.isEmpty(preprocesses)) {
			this.generatePreprocess(batch);
			preprocesses = this.queryManager.selectList(OrderPreprocess.class, query);
		}
		
		// 3. 주문 그룹 정보 조회 - 주문 가공 화면의 중앙 주문 그룹 리스트
//		List<OrderGroup> groups = this.searchOrderGroupList(batch);
		// 4. 호기 정보 조회 - 주문 가공 화면의 우측 호기 리스트
//		List<RackCells> rackCells = this.rackAssignmentStatus(batch);
		// 5. 호기별 물량 요약 정보 - 주문 가공 화면의 우측 상단 호기별 물량 요약 정보
//		List<RtnPreprocessSummary> summaryByRacks = this.preprocessSummaryByRacks(batch);
		// 6. 상품별 물량 요약 정보 - 주문 가공 화면의 좌측 상단 SKU 별 물량 요약 정보
//		Map<?, ?> summaryBySkus = this.preprocessSummary(batch, query);
		// 7. 리턴 데이터 셋
//		return ValueUtil.newMap("regions,groups,preprocesses,summary,group_summary", rackCells, groups, preprocesses, summaryByRacks, summaryBySkus);
		return null;
	}

	@Override
	public int generatePreprocess(JobBatch batch) {
		// 1. 주문 가공 데이터 삭제  
		this.deletePreprocess(batch);
		
		/**
		 * 1. 가공 버튼 클릭시 preprocess에 들어간다.
		 * 4. 
		 */
		
		// 2. 주문 가공 데이터를 생성하기 위해 주문 데이터를 조회
//		String sql = queryStore.getSmsGeneratePreprocessQuery();
//		Map<String, Object> condition = ValueUtil.newMap("domainId,batchId", batch.getDomainId(), batch.getId());
//		List<OrderPreprocess> preprocessList = this.queryManager.selectListBySql(sql, condition, OrderPreprocess.class, 0, 0);
//
//		// 3. 주문 가공 데이터를 추가
//		int generatedCount = ValueUtil.isNotEmpty(preprocessList) ? preprocessList.size() : 0;
//		if(generatedCount > 0) {
//			this.queryManager.insertBatch(preprocessList);
//		}

		// 4. 결과 리턴
//		return generatedCount;
		return 0;
	}

	@Override
	public int deletePreprocess(JobBatch batch) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<JobBatch> completePreprocess(JobBatch batch, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetPreprocess(JobBatch batch, boolean isRackReset, List<String> equipCdList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int assignEquipLevel(JobBatch batch, String equipCds, List<OrderPreprocess> items, boolean automatically) {
		// 1. 상품 정보가 존재하는지 체크
		if(ValueUtil.isEmpty(items)) {
			throw new ElidomRuntimeException("There is no OrderPreprocess!");
		}
		
		// 2. 슈트 지정
		if(automatically) {
			assignChuteByAuto(batch, equipCds, items);
		} else {
			assignChuteByManual(batch, equipCds, items);
		}
		
		return items.size(); 
	}

	@Override
	public int assignSubEquipLevel(JobBatch batch, String equipType, String equipCd, List<OrderPreprocess> items) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void assignChuteByAuto(JobBatch batch, String equipCds, List<OrderPreprocess> items) {
		// 1. 오더타입 조회 (반품, 출고)
		// 2. 슈트별 작업자 그룹이 있는지 확인
		// 3. 1번이 있다면 작업자별 생산성으로 우선순위를 조회한다.
		// 4. 작업할 오더의 우선순위를 조회한다.
		// 5. 3번과 2번의 조합으로 슈트별 오더를 지정 (루프)
		// 6. 주문 가공 정보 업데이트
	}
	
	public void assignChuteByManual(JobBatch batch, String equipCds, List<OrderPreprocess> items) {
		// 1. 오더타입 조회 (반품, 출고)
		// 2. 화면에서 지정한 매장 or 상품을 슈트에 지정한다.
		// 3. 주문 가공 정보 업데이트
	}
	
}