package operato.logis.sms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import operato.logis.sms.query.SmsQueryStore;
import xyz.anythings.base.entity.JobBatch;
import xyz.anythings.base.service.api.IInstructionService;
import xyz.anythings.sys.service.AbstractQueryService;

// 호기복사와 배치 추가가 필요하다.
@Component("smsInstructionService")
public class SmsInstructionService extends AbstractQueryService implements IInstructionService {

	@Autowired
	private SmsQueryStore queryStore;
	
	@Override
	public Map<String, Object> searchInstructionData(JobBatch batch, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int instructBatch(JobBatch batch, List<String> equipIdList, Object... params) {
		// 1. 배치에 대한 상태 체크
		// 2. 작업지시할 수 있는 상태이면 Status Update
		// 3. OrderPreprocess 데이터 삭제
		return 0;
	}

	@Override
	public int instructTotalpicking(JobBatch batch, List<String> equipIdList, Object... params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int mergeBatch(JobBatch mainBatch, JobBatch newBatch, Object... params) {
		// 1. merge 대상 배치 상태 및 상품 or 거래처를 조회한다.
		// 2. 새로운 배치에 대한 상품 or 거래처를 조회한다.
		// 3. 기존 배치에 동일한 상품 or 거래처가 있으면 합치고(merge) 새로운 상품 or 거래처를 할당할 수 있는 chute가 있는지 조회한다.
		// 4. 여유 chute가 부족하다면 실패 merge가능 하다면 배치 Update
		return 0;
	}

	@Override
	public int cancelInstructionBatch(JobBatch batch) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
