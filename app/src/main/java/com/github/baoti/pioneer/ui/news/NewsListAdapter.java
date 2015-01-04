package com.github.baoti.pioneer.ui.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.baoti.pioneer.entity.News;
import com.github.baoti.pioneer.ui.common.holder.LoadingViewHolder;
import com.github.baoti.pioneer.ui.common.page.PageAdapter;
import com.github.baoti.pioneer.ui.common.page.PagePresenter;

import static butterknife.ButterKnife.findById;

/**
 * Created by liuyedong on 2015/1/2.
 */
public class NewsListAdapter extends PageAdapter<News> {
    private final int TYPE_MERCHANT = 0;
    private final int TYPE_LOAD_MORE = 1;

    public NewsListAdapter(LayoutInflater inflater, PagePresenter<News> presenter) {
        super(inflater, presenter);
    }

    @Override
    public void notifyLoadingChanged() {
        notifyItemChanged(getItemCount() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (presenter != null && position == getItemCount() - 1) {
            return TYPE_LOAD_MORE;
        } else {
            return TYPE_MERCHANT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LOAD_MORE) {
            return createLoadingViewHolder(parent);
        }
        return new ViewHolder(inflater.inflate(android.R.layout.simple_list_item_2, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).text1.setText(items.get(position).getTitle());
            ((ViewHolder) holder).text2.setText(items.get(position).getContent());
        }
        if (holder instanceof LoadingViewHolder) {
            bindLoadingViewHolder((LoadingViewHolder) holder);
        }
    }

    @Override
    public int getItemCount() {
        return presenter == null ? items.size() : items.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView text1;
        final TextView text2;

        public ViewHolder(View itemView) {
            super(itemView);
            text1 = findById(itemView, android.R.id.text1);
            text2 = findById(itemView, android.R.id.text2);
        }
    }
}
