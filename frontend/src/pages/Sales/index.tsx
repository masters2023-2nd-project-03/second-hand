import NavBarTitle from '../../components/NavBarTitle';

const SalesPage = () => {
  //TODO(sarang_daddy) : onClick 테스트를 위한 코드 추후 삭제
  const checkTheBackClick = () => {
    console.log('뒤로가기 버튼을 클릭했습니다.');
  };
  const checkTheMoreClick = () => {
    console.log('완료 버튼을 클릭했습니다.');
  };

  return (
    <>
      <NavBarTitle
        prevTitle="닫기"
        type="low"
        backIcon
        preTitleClick={checkTheBackClick}
        rightTitleClick={checkTheMoreClick}
        centerTitle="카탈로그"
        rightTitle="완료"
      />
      <h1>판매내역 이당</h1>
    </>
  );
};

export default SalesPage;
