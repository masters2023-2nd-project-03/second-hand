import { useNavigate } from 'react-router-dom';

import NavBarHome from '../NavBarHome';
import SecondHandItem from '../SecondHandItem';
import { itemList } from '../../mocks/data';
import ErrorPage from '../ErrorPage';

// TODO(sarang_daddy) : API에서 가져오기
const sampleItems = itemList;

const isReusltEmpty: boolean = sampleItems?.length === 0;

const HomePage = () => {
  const navigate = useNavigate();

  const handleIconClick = () => {
    navigate('/Catalog');
  };

  return (
    <>
      <NavBarHome type="medium" iconOnClick={handleIconClick} />
      {!isReusltEmpty ? (
        sampleItems.map((item) => {
          return (
            <li key={item.id}>
              <SecondHandItem
                title={item.title}
                createdAt={item.createdAt}
                status={item.status}
                price={item.price}
                location={item.location}
                chatCount={item.chatCount}
                interestCount={item.interestCount}
                imageURI={item.imageURI}
                option={false}
              />
            </li>
          );
        })
      ) : (
        <ErrorPage />
      )}
    </>
  );
};

export default HomePage;
