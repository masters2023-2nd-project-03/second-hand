import { useNavigate } from 'react-router-dom';

import NavBarHome from '../../components/NavBarHome';
import SecondHandItem from '../../components/SecondHandItem';
import { itemList } from '../../mocks/data';
import ErrorPage from '../Error';

import { CATALOG } from '../../constants/routeUrl';

// TODO(sarang_daddy) : API에서 가져오기
const sampleItems = itemList;

const isReusltEmpty: boolean = sampleItems?.length === 0;

const HomePage = () => {
  const navigate = useNavigate();

  const handleIconClick = () => {
    navigate(CATALOG);
  };

  return (
    <>
      <NavBarHome type="medium" iconOnClick={handleIconClick} />
      {!isReusltEmpty ? (
        <div>
          {sampleItems.map((item) => {
            return (
              <li key={item.id}>
                <SecondHandItem
                  title={item.title}
                  createdAt={item.createdAt}
                  status={item.status as '판매중' | '예약중' | '판매완료'}
                  price={item.price}
                  location={item.location}
                  chatCount={item.chatCount}
                  interestCount={item.interestCount}
                  imageURI={item.imageURI}
                  option={false}
                />
              </li>
            );
          })}
        </div>
      ) : (
        <ErrorPage />
      )}
    </>
  );
};

export default HomePage;
