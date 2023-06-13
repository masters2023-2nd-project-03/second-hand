import NavBarHome from '../NavBarHome';
import SecondHandItem from '../SecondHandItem';
import { itemList } from '../../mocks/data';

const sampleItem = itemList[0];

const HomePage = () => {
  return (
    <>
      <NavBarHome type="low" />
      <h1>홈 이당</h1>
      <SecondHandItem
        id={sampleItem.id}
        title={sampleItem.title}
        createdAt={sampleItem.createdAt}
        status={sampleItem.status}
        price={sampleItem.price}
        location={sampleItem.location}
        chatCount={sampleItem.chatCount}
        interestCount={sampleItem.interestCount}
        imageURI={sampleItem.imageURI}
      />
    </>
  );
};

export default HomePage;
