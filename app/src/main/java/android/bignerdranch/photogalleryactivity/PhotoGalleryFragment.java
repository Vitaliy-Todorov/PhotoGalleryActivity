package android.bignerdranch.photogalleryactivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PhotoGalleryFragment extends Fragment {

    private RecyclerView mPhotoRecyclerView;

    public static PhotoGalleryActivity newInstance() {
        return new PhotoGalleryActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_gallery, container, false);

        mPhotoRecyclerView = view.findViewById(R.id.photo_recycler_view);
        mPhotoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));       //GridLayoutManager - непонятно что еэто, описание из документации: A RecyclerView.LayoutManagerреализация, которая размещает элементы в сетке. По умолчанию каждый элемент занимает 1 пролет. Вы можете изменить его, предоставив пользовательский SpanSizeLookupэкземпляр через setSpanSizeLookup(SpanSizeLookup).

        return view;
    }
}
