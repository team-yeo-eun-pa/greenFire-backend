package yep.greenFire.greenfirebackend.challenge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.challenge.domain.entity.CsList;
import yep.greenFire.greenfirebackend.challenge.domain.repository.CsRepository;
import yep.greenFire.greenfirebackend.challenge.dto.response.CsResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class CsService {
    private final CsRepository csRepository;
    private final CsResponse csResponse;
    private CsList csList ;
    //필요한건가..

    public CsResponse getCsList(String memberId) {
        CsList csList = (CsList) csRepository.findByMemberId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("접근할 수 없습니다."));
        //형변환 박싱... 이었던가
        //csList타입으로 작성되어야 하는데, object타입으로 작성되었다고.
        //내가 접근할 수 없네. 도대체 어떻게 하는거지?
        return csResponse.from(csList);
    }


    public CsResponse getCsDetail(int csCode) {
        CsList detail = (CsList) csRepository.findByCsCode(csCode);

        if(csCode = null) {
            System.out.println("조회할 수 있는 문의 목록이 없습니다. ");
        }

    }
}
