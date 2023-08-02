import elapsedTime from '../../utils/elapsedTime';

interface DetailItemProps {
  title: string;
  salesStatus: '판매중' | '예약중' | '판매완료';
  updatedAt: string;
  chatRoomCount: number;
  watchlistCount: number;
  categoryTitle: string;
  contents: string;
}

// TODO : 물품상세에서 조회수 필요
// TODO : 물품상세에서 판매자 정보 필요 (id, 닉네임)
// TODO : 판매 상태 표시 추가 필요

const DetailItem = ({
  title,
  updatedAt,
  salesStatus,
  chatRoomCount,
  watchlistCount,
  categoryTitle,
  contents,
}: DetailItemProps) => {
  return (
    <>
      <div className="DetailItemContainer">
        <div className="title">{title}</div>
        <div className="subTitle">
          {categoryTitle} - {elapsedTime(updatedAt)}
        </div>
        <div className="content">{contents}</div>
        <div className="count">
          채팅 수 : {chatRoomCount} 관심 수 : {watchlistCount} 조회수 필요
        </div>
      </div>
    </>
  );
};

export default DetailItem;
