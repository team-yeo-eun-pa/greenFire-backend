package yep.greenFire.greenfirebackend.challenge.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.challenge.domain.entity.CsContent;
import yep.greenFire.greenfirebackend.challenge.domain.repository.CsRepository;
import yep.greenFire.greenfirebackend.challenge.dto.request.CsCreateRequest;
import yep.greenFire.greenfirebackend.challenge.dto.response.AdminCsResponse;
import yep.greenFire.greenfirebackend.challenge.dto.response.CsResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class CsService {
    private final CsRepository csRepository;
    private final CsResponse csResponse;



    public CsResponse getCsList(String memberId) {
        CsContent csContent = (CsContent) csRepository.findByMemberId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("접근할 수 없습니다."));
        //형변환 박싱... 이었던가
        //csList타입으로 작성되어야 하는데, object타입으로 작성되었다고.
        //익셉션 타입 변경해주기

        return csResponse.from(csContent);
    }

    public CsContent save(CsCreateRequest csCreateRequest) {
        CsContent newCsContent = csRepository.findByMemberCode(csCreateRequest.getMemberCode());

        final CsContent newList = CsContent.of(
                csCreateRequest.getMemberCode(),
                csCreateRequest.getMemberId(),
                csCreateRequest.getMemberName(),
                csCreateRequest.getMemberEmail()
        );

        final CsContent csContent1 = csRepository.save(newList);


       return newCsContent;
    }

    public CsResponse getCsDetail(int csCode) {
        final CsContent csList = csRepository.findAllCsContents(csCode);
//                .orElseThrow(()-> new NotFoundException(ExceptionCode.NOT_FOUND_CS_CODE));
        //익셉션 코드 문제 해결하기

      return CsResponse.from(csList);

    }


    public AdminCsResponse getAdminCsList(int csCode) {
        CsContent adminCsContent = (CsContent) csRepository.findByAdminCsCode(csCode);

        return AdminCsResponse.from(adminCsContent);
    }
    }


