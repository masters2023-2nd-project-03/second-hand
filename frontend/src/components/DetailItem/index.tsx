import elapsedTime from '../../utils/elapsedTime';

interface DetailItemProps {
  title: string;
  salesStatus: '판매중' | '예약중' | '판매완료';
  updatedAt: string;
  price: number | null;
  location: string;
  chatRoomCount: number;
  watchlistCount: number;
  isWatchlistChecked: boolean;
  imageList: string;
  categoryTitle: string;
  contents: string;
}

// Todo : 물품상세에서는 카테고리 id 필요
// Todo : 물품상세에서는 content 필요

const DetailItem = ({
  title,
  updatedAt,
  salesStatus,
  price,
  location,
  chatRoomCount,
  watchlistCount,
  isWatchlistChecked,
  imageList,
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
