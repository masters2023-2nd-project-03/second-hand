import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { getMyProducts } from '../../api/product';
import { ACCESS_TOKEN } from '../../constants/login';

import * as S from './styles';
import NavBarTitle from '../../components/NavBarTitle';
import { MyItemTap } from '../../components/MyItemsTap';
import { Item } from '../../constants/types';
import SecondHandItem from '../../components/SecondHandItem';
import { ITEM_DETAIL } from '../../constants/routeUrl';
import { ModalStatusButtons } from './ModalStatusButtons';

const SalesPage = () => {
  const navigate = useNavigate();
  const accessToken = localStorage.getItem(ACCESS_TOKEN);

  const [myItemList, setMyItemList] = useState<Item[]>([]);
  const [selected, setSelected] = useState('판매중');
  const [isOptionOpen, setIsOptionOpen] = useState(false);
  const [isDeleteConfirmOpen, setIsDeleteConfirmOpen] = useState(false);

  const isResultEmpty: boolean = myItemList?.length === 0;
  const [currentItem, setCurrentItem] = useState<
    '판매중' | '예약중' | '판매완료' | undefined
  >(undefined);

  const fetchProductsData = async () => {
    const { data: myProductsData } = await getMyProducts(accessToken);
    const curMyItemList = myProductsData?.data.filter((item: Item) => {
      return selected === '판매중'
        ? item.salesStatus === '판매중' || item.salesStatus === '예약중'
        : item.salesStatus === selected;
    });

    setMyItemList(curMyItemList);
  };

  const handleSelectBtn = (status: string) => {
    setSelected(status);
  };

  const handleItemClick = (productId: number) => {
    navigate(`${ITEM_DETAIL}/${productId}`);
  };

  const handleEditClick = (productID: number, event: React.MouseEvent) => {
    event.stopPropagation();
    const selectedItem = myItemList.find(
      (item) => item.productId === productID,
    );
    setCurrentItem(selectedItem?.salesStatus);
    setIsOptionOpen(true);
  };

  const handleCloseModal = () => {
    setIsOptionOpen(false);
  };

  //TODO : 수정 API 대기
  const handleEditModal = () => {
    console.log('게시물을 수정합니다.');
    setIsOptionOpen(false);
  };

  const handleDeleteModal = () => {
    setIsDeleteConfirmOpen(true);
  };

  //TODO : 삭제 API 대기
  // 사용자가 삭제를 확인하면 게시물을 삭제하고 모달을 닫음
  const handleConfirmDelete = () => {
    console.log('게시물을 삭제합니다.');
    setIsOptionOpen(false);
    setIsDeleteConfirmOpen(false);
    // 실제 삭제 로직 API 대기
  };

  const handleCancelDelete = () => {
    setIsDeleteConfirmOpen(false);
  };

  //TODO : 상태변경 API 대기
  const handleChangeStatus = (newStatus: '판매중' | '예약중' | '판매완료') => {
    console.log('상태 변경 API 호출', newStatus);
    // fetchProductsData();
  };

  useEffect(() => {
    fetchProductsData();
  }, [selected]);

  return (
    <>
      <NavBarTitle type="high" centerTitle="판매 내역">
        <MyItemTap status={selected} onChange={handleSelectBtn} />
      </NavBarTitle>
      {!isResultEmpty ? (
        <S.ItemsContainer>
          {myItemList?.map((item: Item) => {
            return (
              <li
                key={item.productId}
                onClick={() => handleItemClick(item.productId)}
              >
                <SecondHandItem
                  title={item.title}
                  updatedAt={item.updatedAt}
                  salesStatus={item.salesStatus}
                  price={item.price}
                  location={item.location}
                  chatRoomCount={item.chatRoomCount}
                  watchListCount={item.watchListCount}
                  isWatchlistChecked={item.isWatchlistChecked}
                  productMainImgUrl={item.productMainImgUrl}
                  isSetEditOption={true}
                  onClick={(event) => handleEditClick(item.productId, event)}
                />
              </li>
            );
          })}
        </S.ItemsContainer>
      ) : (
        <S.Empty>판매 내역이 없습니다.</S.Empty>
      )}
      {isOptionOpen && (
        <S.ModalDim>
          <S.ModalContainer>
            <S.ModalBtns>
              <S.ModalBtn onClick={handleEditModal}>
                <span>게시글 수정</span>
              </S.ModalBtn>
              <ModalStatusButtons
                status={currentItem}
                handleChangeStatus={handleChangeStatus}
              />
              <S.ModalBtn btnType="delete" onClick={handleDeleteModal}>
                <span>삭제</span>
              </S.ModalBtn>
            </S.ModalBtns>
          </S.ModalContainer>
          <S.ModalContainer>
            <S.ModalBtns>
              <S.ModalBtn onClick={handleCloseModal}>
                <span>취소</span>
              </S.ModalBtn>
            </S.ModalBtns>
          </S.ModalContainer>
        </S.ModalDim>
      )}
      {/*TODO : Modal 컴포넌트 만들기*/}
      {isDeleteConfirmOpen && (
        <S.AlertModalDim>
          <S.AlertModalContainer>
            <p>정말로 삭제하겠습니까?</p>
            <S.AlertModalBtns>
              <S.AlertModalBtn onClick={handleConfirmDelete}>
                <span>확인</span>
              </S.AlertModalBtn>
              <S.AlertModalBtn onClick={handleCancelDelete}>
                <span>취소</span>
              </S.AlertModalBtn>
            </S.AlertModalBtns>
          </S.AlertModalContainer>
        </S.AlertModalDim>
      )}
    </>
  );
};

export default SalesPage;
