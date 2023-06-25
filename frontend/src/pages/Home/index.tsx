import { useNavigate } from 'react-router-dom';

import NavBarHome from '../../components/NavBarHome';
import SecondHandItem from '../../components/SecondHandItem';
import ErrorPage from '../Error';
import { CATEGORY, SALESITEM } from '../../constants/routeUrl';
import Button from '../../components/Button';
import * as S from './styles';
import Icon from '../../components/Icon';
import useAsync from '../../hooks/useAsync';
import { getProducts } from '../../api/product';

interface Item {
  productId: number;
  createAt: string;
  title: string;
  salesStatus: '판매중' | '예약중' | '판매완료';
  updatedAt: string;
  price: number | null;
  location: string;
  chatRoomCount: number;
  watchlistCount: number;
  isWatchlistChecked: boolean;
  productMainImgUrl: string;
  option?: boolean;
}

const HomePage = () => {
  const navigate = useNavigate();

  const { data } = useAsync(() => getProducts());
  const itemList = data?.data;
  const isReusltEmpty: boolean = itemList?.length === 0;

  const handleIconClick = () => {
    navigate(CATEGORY);
  };

  const handleFABClick = () => {
    navigate(SALESITEM);
  };

  return (
    <>
      <NavBarHome type="medium" iconOnClick={handleIconClick} />
      {!isReusltEmpty ? (
        <div>
          {itemList?.map((item: Item) => {
            return (
              <li key={item.productId}>
                <SecondHandItem
                  title={item.title}
                  updatedAt={item.updatedAt}
                  salesStatus={
                    item.salesStatus as '판매중' | '예약중' | '판매완료'
                  }
                  price={item.price}
                  location={item.location}
                  chatRoomCount={item.chatRoomCount}
                  watchlistCount={item.watchlistCount}
                  isWatchlistChecked={item.isWatchlistChecked}
                  productMainImgUrl={item.productMainImgUrl}
                  option={false}
                />
              </li>
            );
          })}
        </div>
      ) : (
        <ErrorPage />
      )}
      <S.ButtonPosition>
        <Button circle active onClick={handleFABClick}>
          <Icon name="symbol" width="18" height="20" />
        </Button>
      </S.ButtonPosition>
    </>
  );
};

export default HomePage;
