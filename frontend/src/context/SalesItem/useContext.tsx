import { createContext, Context, Dispatch, SetStateAction } from 'react';

export interface PostObjectType {
  title?: string | null;
  price?: number | null;
  content?: string | null;
  categoryId?: number | null;
  locationId?: number | null;
  memberId?: number | null;
  files?: FormData[] | null;
}

export interface UploadPhotoType {
  postObject: PostObjectType;
  setPostObject: Dispatch<SetStateAction<PostObjectType>>;
}

export const postSalesItemContext: Context<UploadPhotoType> =
  createContext<UploadPhotoType>({
    postObject: {},
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    setPostObject: () => {},
  });
